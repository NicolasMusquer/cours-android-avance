package com.example.kotlincourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_jeu.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class GameViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

class GamesAdapter(private val gameList: List<Game>, val click: (user: Game, isOdd: Boolean) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<GameViewHolder>() {

    override fun getItemCount(): Int {
        return gameList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_jeu, parent, false)
        return GameViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game: Game = gameList[position]
        val isOdd = position % 2 == 0

        holder.view.name.text = game.name

        Glide.with(holder.view)
            .load(game.img)
            .centerCrop()
            .into(holder.view.img)

        holder.view.card.setOnClickListener { click(game, isOdd) }
    }
}