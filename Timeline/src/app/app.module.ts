import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {TopBarComponent} from './top-bar/top-bar.component';
import {TimelineListComponent} from './timeline-list/timeline-list.component';
import {GameComponent} from './game/game.component';
import {EditingComponent} from './editing/editing.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    TimelineListComponent,
    GameComponent,
    EditingComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
        {path: '', component: TimelineListComponent},
        {path: 'game/:gameId', component: GameComponent},
        {path: 'editing/:gameId', component: EditingComponent}
      ]
    ),
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
