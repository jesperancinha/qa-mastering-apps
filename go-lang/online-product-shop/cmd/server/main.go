package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/online-product-shop/internal/model"
	"log"
	"net/http"
)

var products = []model.ProductDto{}

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
	json.NewDecoder(r.Body).Decode(&product)
	product.ID = int64(len(products) + 1)
	products = append(products, product)
	json.NewEncoder(w).Encode(product)
}

func searchProducts(w http.ResponseWriter, r *http.Request) {
	// Simple mock search
	json.NewEncoder(w).Encode(products)
}

func resetProducts(w http.ResponseWriter, r *http.Request) {
	products = []model.ProductDto{}
	w.WriteHeader(http.StatusOK)
}
