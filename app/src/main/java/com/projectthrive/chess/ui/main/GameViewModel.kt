package com.projectthrive.chess.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val gameEngine: GameEngine
    val boardLiveData = MutableLiveData<BoardViewModel>()
    var selectedTile: Position? = null

    init {
        gameEngine = GameEngine()
        boardLiveData.postValue(
            BoardViewModel(pieces = gameEngine.initialPieces())
        )
    }

    fun onPieceClicked(position: Position) {
        boardLiveData.value?.let {
            val currentSelectedTile = selectedTile

            if (currentSelectedTile != null) {
                tryToMovePiece(it, currentSelectedTile, position)
            } else {
                getHighlightedPosition(it, position)
            }
        }
    }

    private fun tryToMovePiece(
        boardViewModel: BoardViewModel,
        currentSelectedTile : Position,
        position: Position
    ) {
        val pieces = gameEngine.tryMove(currentSelectedTile, position)
        selectedTile = null

        boardLiveData.postValue(
            boardViewModel.copy(
                pieces = pieces,
                highlightedPositions = emptyList()
            )
        )
    }

    private fun getHighlightedPosition(
        it: BoardViewModel,
        position: Position
    ) {
        selectedTile = position
        boardLiveData.postValue(
            it.copy(highlightedPositions = gameEngine.getHighlightedPositions(position))
        )
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