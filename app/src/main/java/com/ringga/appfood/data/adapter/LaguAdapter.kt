package com.ringga.appfood.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ringga.appfood.R
import com.ringga.appfood.data.model.ListAudioModel
import com.ringga.appfood.data.service.Preferences.clearMedia
import com.ringga.appfood.data.service.Preferences.setMediaPosition
import com.ringga.appfood.data.service.Preferences.setMediaPositionLagu
import com.ringga.appfood.data.service.Preferences.setMediaUrl
import com.ringga.appfood.databinding.ItemLaguBinding
import com.ringga.appfood.ui.home.music.ListMusicFragment
import com.ringga.appfood.ui.home.music.PlayAudioFragment


class LaguAdapter(
    private var lagus: MutableList<ListAudioModel>,
    private var context: Context,
    private var supportFragmentManager : FragmentManager
) : RecyclerView.Adapter<LaguAdapter.PageHolder>() {

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLaguBinding.bind(view)

    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        with(holder) {
            binding.nama.text = lagus[position].aName
            binding.artis.text = "${lagus[position].aArtist} || ${lagus[position].aAlbum}"

            binding.itemCard.setOnClickListener {
                clearMedia(context)
                setMediaPosition(context, position)
                setMediaPositionLagu(context,position)
                Toast.makeText(context, "${lagus[position].aName}", Toast.LENGTH_SHORT).show()
                setMediaUrl(context,lagus[position].aPath )
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PlayAudioFragment()).commit()

            }
        }
    }

    fun setLagu(r: List<ListAudioModel>) {
        lagus.clear()
        lagus.addAll(r)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        return PageHolder(LayoutInflater.from(context).inflate(R.layout.item_lagu, parent, false))
    }

    override fun getItemCount() = lagus.size
}

