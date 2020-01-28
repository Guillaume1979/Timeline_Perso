import {Component, OnInit} from '@angular/core';
import {TimelineService} from '../timeline.service';
import {ActivatedRoute} from '@angular/router';
import {Card} from '../card';
import {FormBuilder} from '@angular/forms';


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  gameList = this.timelineService.data;
  gameNb;
  cardDeck: Card[];
  foundCard: Card[];
  //cardDeckLength;
  drawCard: Card;
  yearForm = this.formBuilder.group({
    year: '',
  });
  testYear;


  constructor(private timelineService: TimelineService, private route: ActivatedRoute, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    // récupération de l'id du jeu dans l'url
    this.gameNb = this.route.snapshot.paramMap.get('gameId');

    // copie de la collection initiale de carte pour pouvoir en supprimer sans altérer la collection d'origine
    this.cardDeck = this.gameList[this.gameNb].cardList.slice(); // on peut aussi faire : Array.from(this.gameList[this.gameNb].cardList) pour avoir une copie profonde

    // on fixe la taille maximale de la collection de cartes avant de commencer le tirage. Utiliser pour le calcul du numéro de carte aléatoire
    //this.cardDeckLength = this.gameList[this.gameNb].cardList.length;

    // stockage de la carte tirée au hasard
    this.drawCard = this.cardDeck[this.randomPosition()];

    // initialisation du tableau
    this.foundCard = [];
  }


  randomPosition() {
    return Math.floor(Math.random() * this.cardDeck.length);
  }

  // tirage d'une carte aléatoire
  pickACard() {
    let cardPosition = this.randomPosition();
    while (!this.cardDeck[cardPosition]) {
      cardPosition = this.randomPosition();
    }
    return cardPosition;
  }

  onSubmit(yearPlayer) {
    // test pour savoir si la date entrée par le joueur correspond à celle de la carte
    const cardDate = new Date(this.drawCard.date);
    this.testYear = (yearPlayer.year === cardDate.getFullYear());

    // si les années sont égales alors on met la carte dans la partie "cartes trouvées" et on tire une nouvelle carte
    if (this.testYear) {
      // remise à 0 du formulaire
      this.clearForm();
      if (this.cardDeck.length !== 0) {
        this.foundCard.push(this.drawCard);
        this.cardDeck.splice(this.cardDeck.indexOf(this.drawCard), 1);
        if (this.cardDeck.length !== 0) {
          this.drawCard = this.cardDeck[this.randomPosition()];
        }
      }
    }
  }

  clearForm() {
    this.yearForm.patchValue({
      year: 'toto'
    });
  }


}
