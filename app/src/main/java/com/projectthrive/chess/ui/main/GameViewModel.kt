package com.projectthrive.chess.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val gameEngine: GameEngine
    val boardLiveData = MutableLiveData<BoardViewModel>()

    companion object {
        val selectedSpot = MutableLiveData<Pair<Position,PieceViewModel>>(null)
    }


    init {
        gameEngine = GameEngine()
        boardLiveData.postValue(
            BoardViewModel(pieces = gameEngine.initialPieces())
        )
    }

    fun onPieceClicked(position: Position) {
        boardLiveData.value?.let {
            val selectedSquare = selectedSpot.value
            val selectedPiecePosition = selectedSquare?.first
            if (selectedPiecePosition != null) {
                val selectedPiece = selectedSquare.second
                gameEngine.tryMove(selectedPiece, selectedPiecePosition, position)
                boardLiveData.postValue(
                        it.copy(
                                pieces = gameEngine.getBoardState(),
                                highlightedPositions = emptyList()
                        )
                )
            } else {
                boardLiveData.postValue(
                        it.copy(highlightedPositions = gameEngine.getHighlightedPositions(position))
                )
            }
        }
    }
}

/**
 * This is a chess board.
 * It represents a 8x8 square board.
 */
data class BoardViewModel(
    val highlightedPositions: List<Position> = mutableListOf(),
    val pieces: Map<Position, PieceViewModel> = mutableMapOf()
)

/**
 * Positions on a chess board.
 * The top left corner will be x = 0 and y = 0
 */
data class Position(val x: Int, val y: Int)

data class PieceViewModel(
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