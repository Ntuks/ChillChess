package com.projectthrive.chess.ui.main

class GameEngine {
    private val boardState = mutableMapOf<Position, PieceViewModel>()
    private val pieceMovementRules: PieceMovementRules = PieceMovementRules()

    init {
        boardState.putAll(initialPiecesSetup())
        pieceMovementRules.initialPawnsPositions.addAll(boardState.filter { it.value.pieceType == PieceType.PAWN }.keys)
    }

    fun initialPieces() = boardState.toMap()

    fun getHighlightedPositions(position: Position): List<Position> {
        if (boardState[position] == null) {
            return emptyList()
        }

        val piece: PieceViewModel = boardState[position] as PieceViewModel
        return when(piece.pieceType) {
            PieceType.PAWN -> {
                pieceMovementRules.pawnAvailableMovement(boardState, position, piece.color)
            }
            else -> {
                listOf(position.copy(x = position.x + 1))
            }
        }
    }

    fun tryMove(from: Position, to: Position) : Map<Position, PieceViewModel>  {
        val pieceViewModel = boardState[from]

        when (pieceViewModel?.pieceType) {
            PieceType.PAWN -> {
                val validMoves = pieceMovementRules.pawnAvailableMovement(
                    boardState,
                    from,
                    pieceViewModel.color
                ).toSet()

                if (validMoves.contains(to)) {
                    boardState.remove(from)
                    boardState[to] = pieceViewModel
                }
            }
        }

        return boardState.toMap()
    }

    private fun initialPiecesSetup(): Map<Position, PieceViewModel> {
        return mapOf(
            // WHITE BACK PIECES
            Pair(Position(0, 0), PieceViewModel(PieceType.ROOK, PieceColor.WHITE)),
            Pair(Position(0, 1), PieceViewModel(PieceType.KNIGHT, PieceColor.WHITE)),
            Pair(Position(0, 2), PieceViewModel(PieceType.BISHOP, PieceColor.WHITE)),
            Pair(Position(0, 3), PieceViewModel(PieceType.KING, PieceColor.WHITE)),
            Pair(Position(0, 4), PieceViewModel(PieceType.QUEEN, PieceColor.WHITE)),
            Pair(Position(0, 5), PieceViewModel(PieceType.BISHOP, PieceColor.WHITE)),
            Pair(Position(0, 6), PieceViewModel(PieceType.KNIGHT, PieceColor.WHITE)),
            Pair(Position(0, 7), PieceViewModel(PieceType.ROOK, PieceColor.WHITE)),
            // WHITE PAWNS
            Pair(Position(1, 0), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 1), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 2), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 3), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 4), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 5), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 6), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),
            Pair(Position(1, 7), PieceViewModel(PieceType.PAWN, PieceColor.WHITE)),

            // BLACK BACK PIECES
            Pair(Position(7, 0), PieceViewModel(PieceType.ROOK, PieceColor.BLACK)),
            Pair(Position(7, 1), PieceViewModel(PieceType.KNIGHT, PieceColor.BLACK)),
            Pair(Position(7, 2), PieceViewModel(PieceType.BISHOP, PieceColor.BLACK)),
            Pair(Position(7, 3), PieceViewModel(PieceType.KING, PieceColor.BLACK)),
            Pair(Position(7, 4), PieceViewModel(PieceType.QUEEN, PieceColor.BLACK)),
            Pair(Position(7, 5), PieceViewModel(PieceType.BISHOP, PieceColor.BLACK)),
            Pair(Position(7, 6), PieceViewModel(PieceType.KNIGHT, PieceColor.BLACK)),
            Pair(Position(7, 7), PieceViewModel(PieceType.ROOK, PieceColor.BLACK)),
            // BLACK PAWNS
            Pair(Position(6, 0), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 1), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 2), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 3), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 4), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 5), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 6), PieceViewModel(PieceType.PAWN, PieceColor.BLACK)),
            Pair(Position(6, 7), PieceViewModel(PieceType.PAWN, PieceColor.BLACK))
        )
    }
}