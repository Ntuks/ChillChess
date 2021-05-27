package com.projectthrive.chess.ui.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.projectthrive.chess.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        val mainBoard = rootView.findViewById<GridLayout>(R.id.main_board)

        gameViewModel.boardLiveData.observe(this) { boardViewModel ->
            handleViewModelUpdates(mainBoard, boardViewModel)
        }

        return rootView
    }

    private fun handleViewModelUpdates(
        mainBoard: GridLayout,
        boardViewModel: BoardViewModel
    ) {
        mainBoard.removeAllViews()
        val tileMap = mutableMapOf<Position, ImageView>()

        for (i in 0..7) {
            for (j in 0..7) {
                val tile = getTileView(mainBoard)
                val position = Position(i, j)

                tile.background = getColoredTile(i, j)
                tileMap[position] = tile
                mainBoard.addView(tile)

                tile.setOnClickListener { gameViewModel.onPieceClicked(position) }
            }
        }

        boardViewModel.pieces.let {
            for ((position, tile) in tileMap) {
                val pieceSprite = it[position]?.let(this::getPieceSprite)
                tile.setImageDrawable(pieceSprite)
            }
        }

        boardViewModel.highlightedPositions.forEach {
            tileMap[it]?.background = context?.getDrawable(R.drawable.temp_highlight_square)
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

    private fun getPieceSprite(pieceViewModel: PieceViewModel): Drawable? {
        val color = pieceViewModel.color
        return when (pieceViewModel.pieceType) {
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

}