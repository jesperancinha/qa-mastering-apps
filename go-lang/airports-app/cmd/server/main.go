package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/airports-app/internal/model"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/airports-app/internal/service"
	"log"
	"net/http"
)

var queryAirportService *service.QueryAirportService

func main() {
	queryAirportService = &service.QueryAirportService{
		FullAirportInfo: []model.Airport{}, // In a real app, this would be loaded from CSV/DB
	}

	r := mux.NewRouter()
	r.HandleFunc("/jesperancinha-airports/rest/provider/{countrycode}/airports/countrycode", getAirportsByCountryCode).Methods("GET")
	r.HandleFunc("/jesperancinha-airports/rest/provider/{countryname}/airports/countryname", getAirportsByCountryName).Methods("GET")

	log.Println("Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func getAirportsByCountryCode(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	countryCode := vars["countrycode"]
	airports := queryAirportService.GetAirportsByCountryCode(countryCode)
	json.NewEncoder(w).Encode(model.QueryProvider{AirportList: airports})
}

func getAirportsByCountryName(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	countryName := vars["countryname"]
	airports := queryAirportService.GetAirportsByCountryName(countryName)
	json.NewEncoder(w).Encode(model.QueryProvider{AirportList: airports})
}
