import {Component, OnInit} from '@angular/core';
import {Game} from "../game";
import {TimelineService} from "../timeline.service";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-editing',
  templateUrl: './editing.component.html',
  styleUrls: ['./editing.component.css']
})
export class EditingComponent implements OnInit {

  gameList: Game[];
  gameToEdit = new Game();
  gameId;
  tableAttente = new Game();

  constructor(private timelineService: TimelineService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.start();
  }

  start() {
    this.timelineService.getTimeLineObservable().subscribe(
      games => {
        this.gameList = games;
        this.gameId = this.route.snapshot.paramMap.get('gameId');
        console.log('GameList :')
        console.log(this.gameList);
        this.gameToEdit = this.gameList[this.gameId] ? this.gameList[this.gameId] : null;
        console.log('Game To Edit :')
        console.log(this.gameToEdit);
      })
  }

  onSubmit() {
    console.log('gameToEdit avant le push :')
    console.log(this.gameToEdit);
    this.tableAttente = this.gameToEdit;
    console.log('Table attente pour Facebook =');
    console.log(this.tableAttente.cardList[0].name);
    this.timelineService.putTimeLine(this.gameToEdit).pipe().subscribe(
      value => {
        console.log('retour serveur :');
        console.log(value);
        console.log('Table attente pour Facebook =');
        console.log(this.tableAttente.cardList[0].name);

        for (let i = 0; i !== this.gameToEdit.cardList.length; i++) {
          /*console.log('test boucle for '+i)
          console.log('GameList dans la boucle for =');
          console.log(this.gameList[this.gameId].cardList[i].name);
          if (this.tableAttente.cardList[i].name !== this.gameList[this.gameId].cardList[i].name) {
            console.log('test IF = false')*/
            this.timelineService.putUpdatedCard(this.gameId as number, this.tableAttente.cardList[i]).subscribe(
              valueOfReturn => {
                console.log('Retour suite à la mise à jour de la carte :')
                console.log(valueOfReturn);
              }, error => {
              }
            )
          /*} else {console.log('test IF ok pour la carte à l\'emplacement '+i)}*/
        }
        ;

        this.start();
      },
      (error) => {
        console.log(error)
      }
    );


  }

}
