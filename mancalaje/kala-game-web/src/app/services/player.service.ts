import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, retry} from "rxjs/operators";
import {ErrorHandlerService} from "./error-handler.service";

const fetchBoardUSer = "/log/user";

@Injectable({
  providedIn: "root"
})
export class PlayerService {
  constructor(private http: HttpClient, private errorHandler: ErrorHandlerService) {
  }

  public getLoggedUser() {
    return this.http.get(fetchBoardUSer, {responseType: "text"})
      .pipe(retry(3), catchError(this.errorHandler.handleError<string>()));

  }
}
