package com.projectthrive.chess.ui.main

import androidx.lifecycle.ViewModel

class GameModel() : ViewModel() {

}

/**
 * This is a chess board.
 * It represents a 8x8 square board.
 */
data class Board(
        val highlightedPositions: List<Position>,
        val piecesInGame: Map<Position, Piece>,
        val piecesOutOfTheGame: Set<Piece>
)

/**
 * Positions on a chess board.
 * The bottom left corner will be x = 0 and y = 0
 */
data class Position(val x: Int, val y: Int)

data class Piece(
        val pieceType: PieceType,
        val color: PieceColor
)

enum class PieceType {
    PAWN,
    ROOK,
    BISHOP,
    KNIGHT,
    QUEEN,
    KING
}

enum class PieceColor {
    BLACK, WHITE
}