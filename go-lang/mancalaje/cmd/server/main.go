package main

import (
	"encoding/json"
	"github.com/google/uuid"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/mancalaje/internal/model"
	"log"
	"net/http"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/api/current", getCurrentBoard).Methods("GET")
	r.HandleFunc("/api/create", createBoard).Methods("POST")
	r.HandleFunc("/api/move/{boardId}/{pitId}", move).Methods("PUT")
	r.HandleFunc("/api/join/{boardId}", join).Methods("PUT")
	r.HandleFunc("/api/available", getAvailableBoards).Methods("GET")

	log.Println("Mancalaje (Kalah) Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func getCurrentBoard(w http.ResponseWriter, r *http.Request) {
	// Mock implementation
	json.NewEncoder(w).Encode(model.BoardDto{ID: uuid.New()})
}

func createBoard(w http.ResponseWriter, r *http.Request) {
	// Mock implementation
	json.NewEncoder(w).Encode(model.BoardDto{ID: uuid.New()})
}

func move(w http.ResponseWriter, r *http.Request) {
	// Mock implementation
	json.NewEncoder(w).Encode(model.BoardDto{ID: uuid.New()})
}

func join(w http.ResponseWriter, r *http.Request) {
	// Mock implementation
	json.NewEncoder(w).Encode(model.BoardDto{ID: uuid.New()})
}

func getAvailableBoards(w http.ResponseWriter, r *http.Request) {
	// Mock implementation
	json.NewEncoder(w).Encode([]model.BoardDto{})
}
