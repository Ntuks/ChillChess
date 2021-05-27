package com.projectthrive.chess.ui.main

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.projectthrive.chess.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        val mainBoard = rootView.findViewById<GridLayout>(R.id.main_board)

        viewModel = GameViewModel()
        viewModel.boardViewModel.observe(this) { boardViewModel ->
            handleViewModelUpdates(mainBoard, boardViewModel)
        }

        viewModel.boardViewModel.postValue(
            BoardViewModel(pieces = viewModel.initialPiecesSetup())
        )

        return rootView
    }

    private fun handleViewModelUpdates(
        mainBoard: GridLayout,
        boardViewModel: BoardViewModel
    ) {
        mainBoard.removeAllViews()
        val positionToViews = mutableMapOf<Position, ImageView>()

        for (i in 0..7) {
            for (j in 0..7) {
                val tile = getTileView(mainBoard)
                tile.background = getColoredTile(i, j)
                positionToViews[Position(i, j)] = tile
                mainBoard.addView(tile)
            }
        }

        boardViewModel.pieces.let {
            for ((position, square) in positionToViews) {
                val pieceSprite = it[position]?.let(this::getPieceSprite)
                square.setImageDrawable(pieceSprite)
            }
        }
    }

    private fun getTileView(mainBoard: GridLayout?) =
        layoutInflater.inflate(R.layout.square_view, mainBoard, false) as ImageView

    private fun getColoredTile(i: Int, j: Int): Drawable? {
        return if ((i + j) % 2 == 0) {
            context?.getDrawable(R.drawable.white_square)
        } else {
            context?.getDrawable(R.drawable.black_square)
        }
    }

    private fun getPieceSprite(piece: Piece): Drawable? {
        val color = piece.color
        return when (piece.pieceType) {
            PieceType.ROOK -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.drawable.b_rook_png_128px)
            } else {
                context?.getDrawable(R.drawable.w_rook_png_128px)
            }
            PieceType.KNIGHT -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.drawable.b_knight_png_128px)
            } else {
                context?.getDrawable(R.drawable.w_knight_png_128px)
            }
            PieceType.BISHOP -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.drawable.b_bishop_png_128px)
            } else {
                context?.getDrawable(R.drawable.w_bishop_png_128px)
            }
            PieceType.KING -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.drawable.b_king_png_128px)
            } else {
                context?.getDrawable(R.drawable.w_king_png_128px)
            }
            PieceType.QUEEN -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.drawable.b_queen_png_128px)
            } else {
                context?.getDrawable(R.drawable.w_queen_png_128px)
            }
            PieceType.PAWN -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.drawable.b_pawn_png_128px)
            } else {
                context?.getDrawable(R.drawable.w_pawn_png_128px)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
    }

}