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

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: GameModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        val mainBoard = rootView.findViewById<GridLayout>(R.id.main_board)

        for (i in 0..7) {
            for (j in 0..7) {
                val squareView =
                    layoutInflater.inflate(R.layout.square_view, mainBoard, false) as ImageView
                squareView.setImageDrawable(getColoredSquare(i, j))
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameModel::class.java)


    }

}