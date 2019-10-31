package com.example.kotlincourse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.f_list.view.*


class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vu: View = inflater.inflate(R.layout.f_list, container, false)


        vu.swipeRefresh.setOnRefreshListener {
            loadUserList(vu)
        }

        loadUserList(vu)
        return vu
    }


    private fun loadUserList(vu: View) {
        GamesService.getGames({ games ->
            this.activity?.runOnUiThread {
                vu.rc_game.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                vu.swipeRefresh.isRefreshing = false
                vu.rc_game.adapter = GamesAdapter(games) { game, isOdd ->

                    val bundle = Bundle()
                    bundle.putParcelable("game", game)
                    bundle.putBoolean("isOdd", isOdd)

                    val fragment = DetailsFragment()
                    fragment.arguments = bundle

                    this.activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }, {

        })
    }
}
