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
                context?.getDrawable(R.mipmap.black_castle)
            } else {
                context?.getDrawable(R.mipmap.white_castle)
            }
            PieceType.KNIGHT -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.black_knight)
            } else {
                context?.getDrawable(R.mipmap.white_knight)
            }
            PieceType.BISHOP -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.black_bishop)
            } else {
                context?.getDrawable(R.mipmap.white_bishop)
            }
            PieceType.KING -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.black_king)
            } else {
                context?.getDrawable(R.mipmap.white_king)
            }
            PieceType.QUEEN -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.black_queen)
            } else {
                context?.getDrawable(R.mipmap.white_queen)
            }
            PieceType.PAWN -> if (color == PieceColor.BLACK) {
                context?.getDrawable(R.mipmap.black_pawn)
            } else {
                context?.getDrawable(R.mipmap.white_pawn)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameModel::class.java)


    }

}