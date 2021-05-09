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
import com.projectthrive.chess.R
import com.projectthrive.chess.ui.main.GameModel.Companion.piecesInGame

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: GameModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        val mainBoard = rootView.findViewById<GridLayout>(R.id.main_board)

        viewModel = GameModel()
        val pieces = viewModel.initialPiecesSetup()
        piecesInGame.postValue(pieces)

        for (i in 0..7) {
            for (j in 0..7) {
                val squareView =
                    layoutInflater.inflate(R.layout.square_view, mainBoard, false) as ImageView
                if (pieces.containsKey(Position(i,j))) {
                    squareView.setImageDrawable(getPiece(pieceType = pieces[Position(i,j)]!!.pieceType, color = pieces[Position(i,j)]!!.color))
                    squareView.background = getColoredSquare(i, j)
                } else {
                    squareView.setImageDrawable(getColoredSquare(i, j))
                }
                mainBoard.addView(squareView)
            }
        }

        return rootView
    }

    private fun getColoredSquare(i: Int, j: Int) : Drawable? {
        return if ((i + j) % 2 == 0) {
            context?.getDrawable(R.drawable.white_square)
        } else {
            context?.getDrawable(R.drawable.black_square)
        }
    }

    private fun getPiece(pieceType: PieceType,color: PieceColor) : Drawable? {
        return when (pieceType) {
            PieceType.ROOK -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.b_rook_png_128px)
            } else {
                context?.getDrawable(R.mipmap.w_rook_png_128px)
            }
            PieceType.KNIGHT -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.b_knight_png_128px)
            } else {
                context?.getDrawable(R.mipmap.w_knight_png_128px)
            }
            PieceType.BISHOP -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.b_bishop_png_128px)
            } else {
                context?.getDrawable(R.mipmap.w_bishop_png_128px)
            }
            PieceType.KING -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.b_king_png_128px)
            } else {
                context?.getDrawable(R.mipmap.w_king_png_128px)
            }
            PieceType.QUEEN -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.b_queen_png_128px)
            } else {
                context?.getDrawable(R.mipmap.w_queen_png_128px)
            }
            PieceType.PAWN -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.b_pawn_png_128px)
            } else {
                context?.getDrawable(R.mipmap.w_pawn_png_128px)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameModel::class.java)


    }

}