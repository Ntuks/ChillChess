package com.projectthrive.chess.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameModel(): ViewModel() {

    companion object {
        val piecesInGame = MutableLiveData<Map<Position, Piece>>()
        val selectedPiece = MutableLiveData<Piece>()
    }

    fun initialPiecesSetup(): Map<Position, Piece> {
        return mutableMapOf(
            // WHITE BACK PIECES
            Pair(Position(0,0), Piece(PieceType.ROOK, PieceColor.BLACK)),
            Pair(Position(0,1), Piece(PieceType.KNIGHT, PieceColor.BLACK)),
            Pair(Position(0,2), Piece(PieceType.BISHOP, PieceColor.BLACK)),
            Pair(Position(0,3), Piece(PieceType.QUEEN, PieceColor.BLACK)),
            Pair(Position(0,4), Piece(PieceType.KING, PieceColor.BLACK)),
            Pair(Position(0,5), Piece(PieceType.BISHOP, PieceColor.BLACK)),
            Pair(Position(0,6), Piece(PieceType.KNIGHT, PieceColor.BLACK)),
            Pair(Position(0,7), Piece(PieceType.ROOK, PieceColor.BLACK)),
            // WHITE PAWNS
            Pair(Position(1,0), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,1), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,2), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,3), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,4), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,5), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,6), Piece(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(1,7), Piece(PieceType.PAWN, PieceColor.BLACK)),

            // BLACK BACK PIECES
            Pair(Position(7,0), Piece(PieceType.ROOK, PieceColor.WHITE)),
            Pair(Position(7,1), Piece(PieceType.KNIGHT, PieceColor.WHITE)),
            Pair(Position(7,2), Piece(PieceType.BISHOP, PieceColor.WHITE)),
            Pair(Position(7,3), Piece(PieceType.QUEEN, PieceColor.WHITE)),
            Pair(Position(7,4), Piece(PieceType.KING, PieceColor.WHITE)),
            Pair(Position(7,5), Piece(PieceType.BISHOP, PieceColor.WHITE)),
            Pair(Position(7,6), Piece(PieceType.KNIGHT, PieceColor.WHITE)),
            Pair(Position(7,7), Piece(PieceType.ROOK, PieceColor.WHITE)),
            // BLACK PAWNS
            Pair(Position(6,0), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,1), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,2), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,3), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,4), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,5), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,6), Piece(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(6,7), Piece(PieceType.PAWN, PieceColor.WHITE))
        )
    }

}

/**
 * This is a chess board.
 * It represents a 8x8 square board.
 */
data class Board(
        val highlightedPositions: List<Position>,
        val piecesInGame: Map<Position, Piece>?,
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