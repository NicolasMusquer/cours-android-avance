package com.example.kotlincourse


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.f_details.view.*
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.f_details.view.name

class DetailsFragment : Fragment() {

    private var game: Game? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vu: View = inflater.inflate(R.layout.f_details, container, false)

        game = arguments!!.getParcelable("game")
        val isOdd: Boolean = arguments!!.getBoolean("isOdd")

        vu.name.text = game?.name
        vu.description.text = game?.description

        Glide.with(vu)
            .load(game?.img)
            .centerCrop()
            .into(vu.game_image)

        if(isOdd) {
            vu.lien_bouton.setOnClickListener {

                val urlString = game?.link
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")


                val detailsCount = CountManager(this.requireContext()).detailsCount
                CountManager(this.requireContext()).detailsCount = detailsCount?.plus(1)
                Toast.makeText(this.context, detailsCount.toString(), Toast.LENGTH_LONG).show()

                try {
                    context?.startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    intent.setPackage(null)
                    context?.startActivity(intent)
                }
            }
        } else {
            vu.lien_bouton.setOnClickListener {

                val bundle = Bundle()
                bundle.putString("url", game?.link)

                val frag = WebFragment()
                frag.arguments = bundle

                this.activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fContainer, frag)
                    .addToBackStack(null)
                    .commit()


                val detailsCount = CountManager(this.requireContext()).detailsCount
                CountManager(this.requireContext()).detailsCount = detailsCount?.plus(1)
                Toast.makeText(this.context, detailsCount.toString(), Toast.LENGTH_LONG).show()
            }
        }
        return vu
    }
}
