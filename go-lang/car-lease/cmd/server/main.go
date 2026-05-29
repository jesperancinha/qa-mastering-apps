package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/car-lease/internal/model"
	"log"
	"net/http"
)

var cars = []model.CarDto{}

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/cars", listCars).Methods("GET")
	r.HandleFunc("/cars", createCar).Methods("POST")

	log.Println("Car Lease Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func listCars(w http.ResponseWriter, r *http.Request) {
	json.NewEncoder(w).Encode(cars)
}

func createCar(w http.ResponseWriter, r *http.Request) {
	var car model.CarDto
	json.NewDecoder(r.Body).Decode(&car)
	car.ID = int64(len(cars) + 1)
	cars = append(cars, car)
	json.NewEncoder(w).Encode(car)
}
