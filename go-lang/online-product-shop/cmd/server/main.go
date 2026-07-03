package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/online-product-shop/internal/model"
	"log"
	"net/http"
	"sync"
)

var (
	productsMu sync.RWMutex
	products   = []model.ProductDto{}
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/products", createProduct).Methods("POST")
	r.HandleFunc("/products/search", searchProducts).Methods("GET").Queries("q", "{q}")
	r.HandleFunc("/products/reset", resetProducts).Methods("POST")

	log.Println("Online Product Shop Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func createProduct(w http.ResponseWriter, r *http.Request) {
	var product model.ProductDto
	if err := json.NewDecoder(r.Body).Decode(&product); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	productsMu.Lock()
	product.ID = int64(len(products) + 1)
	products = append(products, product)
	productsMu.Unlock()
	json.NewEncoder(w).Encode(product)
}

func searchProducts(w http.ResponseWriter, r *http.Request) {
	// Simple mock search
	productsMu.RLock()
	defer productsMu.RUnlock()
	json.NewEncoder(w).Encode(products)
}

func resetProducts(w http.ResponseWriter, r *http.Request) {
	productsMu.Lock()
	products = []model.ProductDto{}
	productsMu.Unlock()
	w.WriteHeader(http.StatusOK)
}
