import {Injectable} from "@angular/core";
import {Observable, throwError} from "rxjs";

/**
 * Shared HTTP error handling for services and components.
 *
 * Logs the failure and re-throws it (instead of swallowing it into
 * `of(undefined)`) so callers can surface the error to the user, e.g. by
 * subscribing with an `error` handler or piping further with `catchError`.
 */
@Injectable({
  providedIn: "root"
})
export class ErrorHandlerService {
  handleError<T>(operation = "operation") {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return throwError(() => error);
    };
  }
}
