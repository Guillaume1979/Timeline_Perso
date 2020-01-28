package co.simplon.timelineapi.controller;

import co.simplon.timelineapi.model.Card;
import co.simplon.timelineapi.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeline/{tid}/card")
@CrossOrigin("http://localhost:4200")
public class CardController {

    private CardService cardService;

    /**
     * Constructeur du CardController
     *
     * @param cardService le cardService qui sera injecté par Spring
     */
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Fonction de récupération de la liste des cartes d'un timeline en base.
     *
     * @param tid l'id du timeline sur lequel aller chercher les cartes
     * @return La liste de toutes les carets d'un timeline en base
     */
    @GetMapping
    public List<Card> getCardList(@PathVariable Long tid) {
        return this.cardService.getTimelineCards(tid);
    }

    /**
     * Fonction de sauvegarde d'une nouvelle carte.
     *
     * @param newCard la nouvelle carte à créer (venant du front)
     * @return la nouvelle carte créée par la base si réussite, code 400 sinon
     */
    @PostMapping
    public ResponseEntity<Card> createCard(@PathVariable Long tid, @RequestBody Card newCard) {
        try {
            return ResponseEntity.ok(this.cardService.saveCard(newCard, tid));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Fonction de mise à jour d'une nouvelle carte.
     *
     * @param card la nouvelle carte à créer (venant du front)
     * @return la carte mise à jour par la base si réussite, code 400 sinon
     */
    @PutMapping
    public ResponseEntity<Card> updateCard(@PathVariable Long tid, @RequestBody Card card) {
        try {
            return ResponseEntity.ok(this.cardService.saveCard(card, tid));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Fonction de suppression d'une carte.
     *
     * @param tid l'id du timeline associé
     * @param cid l'id de la carte à supprimer
     */
    @DeleteMapping("/{cid}")
    public void deleteCard(@PathVariable Long tid, @PathVariable Long cid) {
        this.cardService.deleteCard(cid);
    }
}
