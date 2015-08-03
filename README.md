## RoadMap :

### Done :
- [x] Object should not be build if it doesn't have all required attributes to work by their own.
- [x] Use custom Exception.
- [x] Add test to ensure object cannot be built in a states that they don't fully controls. An object should not rely on outside access, (basically no setter allowed).
- [x] Players should be able to move a piece.
- [x] Players should be able to capture another piece.
- [x] Game should ensure player can only play one by one when it is their turn.
- [x] Game should be Thread safe.
- [x] Implement a Factory which handle board creation.

### In progress :
- [ ] Add point to a player when capturing an opponent piece.
- [ ] Add a logger.

### On deck :
- [ ] Remove the ability for a pawn to execute a "firstMove" action after his first move.
- [ ] Game should end when a player is un check-mate state.
- [ ] Missing a layer in game, move should be handled by player instead of game directly.
- [ ] Implements special moves for king (Castling).
- [ ] Implement a system allowing user to save a game for latter. (Basically save each moves and reapply these one by one when resuming).
- [ ] Encrypt the file containing history of moves, to ensure a player can't change the list of moves when he had saved the game.
- [ ] Create a GUI.
