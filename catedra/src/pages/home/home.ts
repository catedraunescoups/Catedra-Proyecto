import { Component } from '@angular/core';
import { NavController, PopoverController, NavParams, Platform } from 'ionic-angular';
import {Http} from '@angular/http';
import { Storage } from '@ionic/storage';
import { MenuController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import 'rxjs/add/operator/map';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})

export class HomePage {

  countries: string[];
  publicaion: string[];
  HomePage = "homePage";
  NoticiasPage = "noticiasPage";
  InformacionPage = "informacionPage";
  roomkey:string;
  nickname:string;
  errorMessage: string;
  public rootPage: any;
  constructor(
    public navCtrl: NavController,public proveedor:RestProvider, 
    public http: Http,public menuCtrl: MenuController, public popoverCtrl: PopoverController,public navParams: NavParams, public storage:Storage, public platform: Platform) {
      
  }
  ionViewDidLoad() {
    this.getCountries();
    this.storage.get('usuario').then((val) => {
      console.log('su nombre es', val);
    });
    
  }
  getPE() {
    this.proveedor.getPE()
       .subscribe(
         publicaion => this.publicaion = publicaion);
  }
  getCountries() {
    this.proveedor.getCountries()
       .subscribe(
         countries => this.countries = countries);
  }
  openMenu() {
    this.menuCtrl.open();
  }
  abrirHomePag() {
    this.navCtrl.push("HomePage");
  }
  abrirNotiPag() {
    this.navCtrl.push("NoticiasPage");
  }
  abrirInfoPag() {
    this.navCtrl.push("InformacionPage");
  }

  backButtonEvent(){
    this.platform.backButton.subscribe(()=>{
    navigator['app'].irAtras();
 })}


  irAtras() {
    this.navCtrl.pop();
  }

  irPrincipal() {
    this.navCtrl.popToRoot();
  }
 
}
