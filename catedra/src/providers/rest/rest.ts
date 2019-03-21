import { Http,Response,  Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

/*
  Generated class for the RestProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RestProvider {

  //private apiUrlP = 'http://192.168.1.120:8080/CatedraProyecto/srv/personas/listarpersonas';
  //private apiUrl =  'http://192.168.1.120:8080/CatedraProyecto/srv/publicacion/listarpublicacionesEventos';
  //private apiUrlN = 'http://192.168.1.120:8080/CatedraProyecto/srv/publicacion/listarpublicacionesNoticias';
  //private apiUrlI = 'http://192.168.1.120:8080/CatedraProyecto/srv/publicacion/listarpublicacionesInformacion';
  //private apiUrlEP = 'http://192.168.1.120:8080/CatedraProyecto/srv/publicacion/listarpublicacionesEP';

  private apiUrlP = 'http://localhost:8080/CatedraProyecto/srv/personas/listarpersonas';
  private apiUrl = 'http://localhost:8080/CatedraProyecto/srv/publicacion/listarpublicacionesEventos';
  private apiUrlN = 'http://localhost:8080/CatedraProyecto/srv/publicacion/listarpublicacionesNoticias';
  private apiUrlI = 'http://localhost:8080/CatedraProyecto/srv/publicacion/listarpublicacionesInformacion';
  private apiUrlEP='http://localhost:8080/CatedraProyecto/srv/publicacion/listarpublicacionesEP';
  access: boolean;
 
   constructor(public http: Http) {}
   

  getCountries(): Observable <string[]> {
    return this.http.get(this.apiUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }
  getNoticias(): Observable <string[]> {
    return this.http.get(this.apiUrlN)
                    .map(this.extractData)
                    .catch(this.handleError);
  }
  getInformacion(): Observable <string[]> {
    return this.http.get(this.apiUrlI)
                    .map(this.extractData)
                    .catch(this.handleError);
  }
  getUsuarios(): Observable <string[]> {
    return this.http.get(this.apiUrlP)
                    .map(this.extractDataU)
                    .catch(this.handleError);
  }
  getPE(): Observable <string[]> {
    return this.http.get(this.apiUrlEP)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  private extractDataU(res: Response) {
    let body = res.json();
    return body || { };
  }
  
  private extractData(res: Response) {
    let body = res.json();
    console.log(body)
    return body || { };
  }

  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
  
  getUsers(credentials, type) {
    return new Promise((resolve, reject) => {
      var headers = new Headers();
      headers.append('Access-Control-Allow-Origin' , '*');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, PUT');
      headers.append('Accept','application/json');
      headers.append('content-type','application/json');

      this.http.post(this.apiUrlP + type, JSON.stringify(credentials), {headers: headers})
        .subscribe(res => {
          resolve(res.json());
        }, (err) => {
          reject(err);
        });
    });
  }
  
}

