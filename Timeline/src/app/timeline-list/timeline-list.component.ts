import {Component, OnInit} from '@angular/core';
import {TimelineService} from '../timeline.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Game} from '../game';
import {generateAnalysis} from '@angular/compiler-cli/src/ngtsc/indexer/src/transform';

@Component({
  selector: 'app-timeline-list',
  templateUrl: './timeline-list.component.html',
  styleUrls: ['./timeline-list.component.css']
})
export class TimelineListComponent implements OnInit {

  gamesList;

  constructor(private timelineService: TimelineService, private httpClient: HttpClient) {
    // Liaison sur les données en local dans le Service
    //this.gamesList = this.timelineService.data;
    this.timelineService.getTimeLineObservable().subscribe(
    (games: Game[]) => {this.gamesList = games;}
    );

  }

  ngOnInit() {
  }

  countCard(id) {
    return this.gamesList[id - 1].cardList.length;
  }

  deleteGame(gameId) {
    this.gamesList.splice(gameId, 1);
  }

}
