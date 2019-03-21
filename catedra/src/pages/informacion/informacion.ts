import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import {Http} from '@angular/http';
import {RoomPage} from '../room/room';
import { Storage } from '@ionic/storage';
/**
 * Generated class for the InformacionPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-informacion',
  templateUrl: 'informacion.html',
})
export class InformacionPage {
  data = { nickname:" " };
  informacion: string[];
  errorMessage: string;

  constructor(
    public navCtrl: NavController,public proveedor:RestProvider, 
    public http: Http, public storage:Storage) {
  }
  ionViewDidLoad() {
    this.getInformacion();
    this.storage.get('usuario').then((val) => {
      console.log('su nombre es', val);
    });
  }
  getInformacion() {
    this.proveedor.getInformacion()
       .subscribe(
         informacion => this.informacion = informacion);
  }
  enterNickname() {
    this.navCtrl.setRoot(RoomPage, {
      nickname: this.data.nickname
    });
}

}