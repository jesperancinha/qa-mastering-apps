package main

import (
	"encoding/json"
	"github.com/google/uuid"
	"github.com/gorilla/mux"
	"github.com/jesperancinha/qa-mastering-apps/go-lang/supermarket/internal/model"
	"log"
	"net/http"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/deliveries", createDelivery).Methods("POST")
	r.HandleFunc("/deliveries/invoice", getInvoices).Methods("POST")
	r.HandleFunc("/deliveries/business-summary", getBusinessSummary).Methods("GET")

	log.Println("Supermarket Server starting on port 8080...")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func createDelivery(w http.ResponseWriter, r *http.Request) {
	var request model.DeliveryRequestDto
	json.NewDecoder(r.Body).Decode(&request)
	response := model.DeliveryResponseDto{
		ID:        uuid.New().String(),
		VehicleID: request.VehicleID,
		Address:   request.Address,
		StartedAt: request.StartedAt,
		Status:    request.Status,
	}
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode(response)
}

func getInvoices(w http.ResponseWriter, r *http.Request) {
	var request model.InvoiceRequestDto
	json.NewDecoder(r.Body).Decode(&request)
	var invoices []model.InvoiceResponseDto
	for _, id := range request.DeliveryIDs {
		invoices = append(invoices, model.InvoiceResponseDto{
			DeliveryID: id,
			InvoiceID:  uuid.New().String(),
		})
	}
	json.NewEncoder(w).Encode(invoices)
}

func getBusinessSummary(w http.ResponseWriter, r *http.Request) {
	summary := model.DeliveriesSummaryDto{
		Deliveries:                         10,
		AverageMinutesBetweenDeliveryStart: 15,
	}
	json.NewEncoder(w).Encode(summary)
}
