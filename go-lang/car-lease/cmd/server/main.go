package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/car-lease/internal/model"
	"log"
	"net/http"
	"sync"
)

var (
	carsMu sync.RWMutex
	cars   = []model.CarDto{}
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/cars", listCars).Methods("GET")
	r.HandleFunc("/cars", createCar).Methods("POST")

	log.Println("Car Lease Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func listCars(w http.ResponseWriter, r *http.Request) {
	carsMu.RLock()
	defer carsMu.RUnlock()
	json.NewEncoder(w).Encode(cars)
}

func createCar(w http.ResponseWriter, r *http.Request) {
	var car model.CarDto
	if err := json.NewDecoder(r.Body).Decode(&car); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	carsMu.Lock()
	car.ID = int64(len(cars) + 1)
	cars = append(cars, car)
	carsMu.Unlock()
	json.NewEncoder(w).Encode(car)
}
