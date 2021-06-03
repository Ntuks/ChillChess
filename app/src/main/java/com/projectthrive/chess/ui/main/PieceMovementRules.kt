package com.projectthrive.chess.ui.main

class PieceMovementRules() {
    val initialPawnsPositions = mutableSetOf<Position>()

    fun pawnAvailableMovement(
            boardState: Map<Position,PieceViewModel>,
            position: Position,
            pieceColor: PieceColor
    ): List<Position> {

        val validMoves = mutableListOf<Position>()
        if (pieceColor == PieceColor.WHITE){
            when {
                boardState[Position(position.x + 2, position.y)] != null -> {
                    validMoves.add(Position(position.x + 1, position.y))
                }
                boardState[Position(position.x + 1, position.y)] == null -> {
                    if (initialPawnsPositions.contains(position)) {
                        validMoves.add(Position(position.x + 1, position.y))
                        validMoves.add(Position(position.x + 2, position.y))
                    } else {
                        validMoves.add(Position(position.x + 1, position.y))
                    }
                }
            }

            boardState[Position(position.x + 1, position.y + 1)]?.let {
                if (it.color != pieceColor) {
                    validMoves.add(Position(position.x + 1, position.y + 1))
                }
            }

            boardState[Position(position.x + 1, position.y - 1)]?.let {
                if (it.color != pieceColor) {
                    validMoves.add(Position(position.x + 1, position.y - 1))
                }
            }
        } else {
            when {
                boardState[Position(position.x - 2, position.y)] != null -> {
                    validMoves.add(Position(position.x - 1, position.y))
                }
                boardState[Position(position.x - 1, position.y)] == null -> {
                    if (initialPawnsPositions.contains(position)) {
                        validMoves.add(Position(position.x - 1, position.y))
                        validMoves.add(Position(position.x - 2, position.y))
                    } else {
                        validMoves.add(Position(position.x - 1, position.y))
                    }
                }
            }

            boardState[Position(position.x - 1, position.y - 1)]?.let {
                if (it.color != pieceColor) {
                    validMoves.add(Position(position.x - 1, position.y - 1))
                }
            }

            boardState[Position(position.x - 1, position.y + 1)]?.let {
                if (it.color != pieceColor) {
                    validMoves.add(Position(position.x - 1, position.y + 1))
                }
            }
        }
        return validMoves.toList()
    }

}
