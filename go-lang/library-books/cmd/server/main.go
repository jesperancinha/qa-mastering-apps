package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/library-books/internal/model"
	"log"
	"net/http"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/{volume}", getSpecificVolume).Methods("GET")
	r.HandleFunc("/", getVolumesByTextAndLanguage).Methods("GET").Queries("query", "{query}", "language", "{language}")

	log.Println("Library Books Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func getSpecificVolume(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	volume := vars["volume"]
	// Mock implementation
	result := model.SearchResult{
		Title: "Mock Volume " + volume,
		Author: "Mock Author",
	}
	json.NewEncoder(w).Encode(result)
}

func getVolumesByTextAndLanguage(w http.ResponseWriter, r *http.Request) {
	query := r.URL.Query().Get("query")
	language := r.URL.Query().Get("language")
	// Mock implementation
	results := []model.SearchResult{
		{
			Title: "Mock result for " + query + " in " + language,
			Author: "Mock Author",
		},
	}
	json.NewEncoder(w).Encode(results)
}
