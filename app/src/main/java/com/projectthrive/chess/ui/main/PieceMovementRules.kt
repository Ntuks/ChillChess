package com.projectthrive.chess.ui.main

class PieceMovementRules {
    companion object {
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
                        validMoves.add(Position(position.x + 1, position.y))
                        validMoves.add(Position(position.x + 2, position.y))
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
                        validMoves.add(Position(position.x - 1, position.y))
                        validMoves.add(Position(position.x - 2, position.y))
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
//            if (isMadeFirstMove) {
//                
//            } else {
//                if (color == PieceColor.WHITE){ validMoves.add(Position(position.x, position.y + 1)) }
//                else { validMoves.add(Position(position.x, position.y - 1)) }
//            }
            return validMoves.toList()
        }
    }
}
