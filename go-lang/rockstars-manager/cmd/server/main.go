package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/rockstars-manager/internal/model"
	"log"
	"net/http"
	"strconv"
	"sync"
)

var (
	artistsMu sync.RWMutex
	artists   = make(map[int64]model.ArtistDto)
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/artists/{id}", getArtist).Methods("GET")
	r.HandleFunc("/artists/filter/name/{artistName}", getArtistByName).Methods("GET")
	r.HandleFunc("/artists", createArtist).Methods("POST")
	r.HandleFunc("/artists/{id}", updateArtist).Methods("PUT")
	r.HandleFunc("/artists/{id}", deleteArtist).Methods("DELETE")

	log.Println("Rockstars Manager Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func getArtist(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, err := strconv.ParseInt(vars["id"], 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	artistsMu.RLock()
	artist, ok := artists[id]
	artistsMu.RUnlock()
	if ok {
		json.NewEncoder(w).Encode(artist)
	} else {
		w.WriteHeader(http.StatusNotFound)
	}
}

func getArtistByName(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	name := vars["artistName"]
	artistsMu.RLock()
	defer artistsMu.RUnlock()
	for _, artist := range artists {
		if artist.Name == name {
			json.NewEncoder(w).Encode(artist)
			return
		}
	}
	w.WriteHeader(http.StatusNotFound)
}

func createArtist(w http.ResponseWriter, r *http.Request) {
	var artist model.ArtistDto
	if err := json.NewDecoder(r.Body).Decode(&artist); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	artistsMu.Lock()
	artist.ID = int64(len(artists) + 1)
	artists[artist.ID] = artist
	artistsMu.Unlock()
	json.NewEncoder(w).Encode(artist)
}

func updateArtist(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, err := strconv.ParseInt(vars["id"], 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	var artist model.ArtistDto
	if err := json.NewDecoder(r.Body).Decode(&artist); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	artist.ID = id
	artistsMu.Lock()
	artists[id] = artist
	artistsMu.Unlock()
	json.NewEncoder(w).Encode(artist)
}

func deleteArtist(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, err := strconv.ParseInt(vars["id"], 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	artistsMu.Lock()
	delete(artists, id)
	artistsMu.Unlock()
	w.WriteHeader(http.StatusOK)
}
