package co.simplon.timelineapi.controller;

import co.simplon.timelineapi.model.Timeline;
import co.simplon.timelineapi.service.TimelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeline")
@CrossOrigin("http://localhost:4200")
public class TimelineController {

    private TimelineService timelineService;

    /**
     * Constructeur du TimelineController
     *
     * @param timelineService le timelineService qui sera injecté par Spring
     */
    public TimelineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    /**
     * Fonction de récupération de la liste des timelines en base.
     *
     * @return La liste de tous les timelines en base
     */
    @GetMapping
    public List<Timeline> getTimelineList() {
        return this.timelineService.getAllTimelines();
    }

    /**
     * Fonction de sauvegarde d'un nouveau timeline.
     *
     * @param newTimeline le nouveau timeline à créer (venant du front)
     * @return le nouveau timeline créé par la base si réussite, code 400 sinon
     */
    @PostMapping
    public ResponseEntity<Timeline> createTimeline(@RequestBody Timeline newTimeline) {
        /*
         * Pour faire propre si on réussi, on renvoie le nouveau timeline avec un code
         * 200. Sinon on renvoie un code d'erreur 400
         */
        try {
            return ResponseEntity.ok(this.timelineService.saveTimeline(newTimeline));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Fonction de mise à jour d'un timeline.
     *
     * @param timeline le timeline à mettre à jour
     * @return le timeline mis à jour par la base si réussite, code 400 sinon
     */
    @PutMapping
    public ResponseEntity<Timeline> updateTimeline(@RequestBody Timeline timeline) {
        /*
         * Pour faire propre si on réussi, on renvoie le nouveau timeline avec un code
         * 200. Sinon on renvoie un code d'erreur 400
         */
        try {
            return ResponseEntity.ok(this.timelineService.saveTimeline(timeline));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Fonction de suppression d'un timeline.
     *
     * @param tid l'id du timeline à supprimer.
     */
    @DeleteMapping("/{tid}")
    public void deleteTimeline(@PathVariable Long tid) {
        this.timelineService.deleteTimeline(tid);
    }

}