package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/rockstars-manager/internal/model"
	"log"
	"net/http"
	"strconv"
)

var artists = make(map[int64]model.ArtistDto)

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
	id, _ := strconv.ParseInt(vars["id"], 10, 64)
	if artist, ok := artists[id]; ok {
		json.NewEncoder(w).Encode(artist)
	} else {
		w.WriteHeader(http.StatusNotFound)
	}
}

func getArtistByName(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	name := vars["artistName"]
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
	json.NewDecoder(r.Body).Decode(&artist)
	artist.ID = int64(len(artists) + 1)
	artists[artist.ID] = artist
	json.NewEncoder(w).Encode(artist)
}

func updateArtist(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, _ := strconv.ParseInt(vars["id"], 10, 64)
	var artist model.ArtistDto
	json.NewDecoder(r.Body).Decode(&artist)
	artist.ID = id
	artists[id] = artist
	json.NewEncoder(w).Encode(artist)
}

func deleteArtist(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, _ := strconv.ParseInt(vars["id"], 10, 64)
	delete(artists, id)
	w.WriteHeader(http.StatusOK)
}
