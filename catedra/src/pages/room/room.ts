import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import * as firebase from 'Firebase';
import { ChatPage } from '../chat/chat';
import { AddroomPage } from '../addroom/addroom';
import { Storage } from '@ionic/storage';


@IonicPage()
@Component({
  selector: 'page-room',
  templateUrl: 'room.html',
})
export class RoomPage {
  rooms = [];
  data = { nickname:" " ,password:" "};
  ref = firebase.database().ref('chatrooms/');

  constructor(public navCtrl: NavController, public navParams: NavParams, public storage:Storage) {
    this.ref.on('value', resp => {
      this.rooms = [];
      this.rooms = snapshotToArray(resp);
      
    });
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad RoomPage');
    this.storage.get('usuario').then((val) => {
      console.log('su nombre es', val);
    });
  }
  
  addRoom() {
    this.navCtrl.push(AddroomPage);
  }

  joinRoom(key) {
    this.navCtrl.setRoot(ChatPage, {
      key:key,
      nickname:this.navParams.get("nickname")
    });
  }
  enterNickname() {
    this.navCtrl.setRoot(RoomPage, {
      nickname: this.data.nickname,
      password: this.data.password
    });
  }
  
 
}

export const snapshotToArray = snapshot => {
    let returnArr = [];

    snapshot.forEach(childSnapshot => {
        let item = childSnapshot.val();
        item.key = childSnapshot.key;
        returnArr.push(item);
    });

    return returnArr;
};
