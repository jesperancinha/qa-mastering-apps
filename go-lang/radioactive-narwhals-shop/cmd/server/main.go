package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/radioactive-narwhals-shop/internal/model"
	"log"
	"net/http"
	"strconv"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/load", loadNarwhals).Methods("POST")
	r.HandleFunc("/stock/{days}", getStocks).Methods("GET")
	r.HandleFunc("/narwhals/{days}", getNarwhals).Methods("GET")
	r.HandleFunc("/order/{days}", order).Methods("POST")

	log.Println("Radioactive Narwhals Shop Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func loadNarwhals(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusResetContent)
}

func getStocks(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	days, _ := strconv.Atoi(vars["days"])
	stocks := []model.Stock{
		{Name: "Radioactive Food", Quantity: 100 - days},
	}
	json.NewEncoder(w).Encode(stocks)
}

func getNarwhals(w http.ResponseWriter, r *http.Request) {
	narwhals := model.CurrentNarwhals{
		Narwhals: []model.Narwhal{
			{Name: "Sparky", Age: 5},
		},
	}
	json.NewEncoder(w).Encode(narwhals)
}

func order(w http.ResponseWriter, r *http.Request) {
	var customerOrder model.CustomerOrder
	json.NewDecoder(r.Body).Decode(&customerOrder)
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(customerOrder)
}
