import {Component} from '@angular/core';
import {catchError, retry} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {ErrorHandlerService} from "./services/error-handler.service";
import {environment} from "../environments/environment";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: false
})
export class AppComponent {
  title = "kala-game-web";

  constructor(private http: HttpClient, private router: Router, private errorHandler: ErrorHandlerService) {
  }

  logout() {
    this.http.post("/logout", {}).pipe(
      retry(3), catchError(this.errorHandler.handleError<string>())).subscribe(() => {
        window.location.href = environment.loginRedirectUrl;
      }
    );
  }
}
