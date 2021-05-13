package com.projectthrive.chess.ui.main

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.View.OnTouchListener
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.projectthrive.chess.R
import com.projectthrive.chess.ui.main.GameModel.Companion.piecesInGame
import com.projectthrive.chess.ui.main.GameModel.Companion.selectedPiece


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG: String = "MAIN_FRAGMENT"
    }

    private val letters = mutableListOf("a", "b", "c", "d", "e", "f", "g", "h")

    private lateinit var gestureDetector: GestureDetector

    private lateinit var viewModel: GameModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        val mainBoard = rootView.findViewById<GridLayout>(R.id.main_board)

        gestureDetector = GestureDetector(requireContext(), SingleTapConfirm())

        viewModel = GameModel()
        val pieces = viewModel.initialPiecesSetup()
        piecesInGame.postValue(pieces)

        for (i in 0..7) {
            for (j in 0..7) {
                var squareView: View

                if (pieces.containsKey(Position(i, j))) {
                    squareView = layoutInflater.inflate(R.layout.square_view, mainBoard, false) as ImageView
                    squareView.setImageDrawable(getPiece(pieceType = pieces[Position(i, j)]!!.pieceType, color = pieces[Position(i, j)]!!.color))
                    squareView.background = getColoredSquare(i, j)

                    squareView.setOnTouchListener(OnTouchListener { v, event ->
                        v.performClick()

                        val validMoves = mainBoard[23] as ImageView
                        selectedPiece.postValue(pieces[Position(i, j)])
                        if (gestureDetector.onTouchEvent(event) || gestureDetector.isLongpressEnabled) {
                            // Highlight the squares of the selected piece
                            validMoves.foreground = context?.getDrawable(R.drawable.highlight_square)
                            return@OnTouchListener true
                        }
                        false
                    })

                    mainBoard.addView(squareView)
                } else {
                    squareView = layoutInflater.inflate(R.layout.square_view, mainBoard, false) as ImageView
                    squareView.setImageDrawable(getColoredSquare(i, j))

                    squareView.setOnTouchListener(OnTouchListener { v, event ->
                        val validMoves = mainBoard[23] as ImageView
                        if (gestureDetector.onTouchEvent(event)) {
                            v.performClick()
                            validMoves.foreground = null
                            return@OnTouchListener true
                        }
                        false
                    })

                    mainBoard.addView(squareView)
                }
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

    private fun getSquareNotation(i: Int, j: Int): String {
        return letters[j] + (letters.size - i)
    }

    private fun getPiece(pieceType: PieceType, color: PieceColor) : Drawable? {
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

    private class SingleTapConfirm : SimpleOnGestureListener() {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun onDown(e: MotionEvent?): Boolean {
            if (e != null) {
                Log.d(MainFragment.TAG, "EVENT: ${e.classification}")
            }
            return true
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return true
        }
    }
}