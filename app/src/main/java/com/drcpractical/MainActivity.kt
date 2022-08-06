package com.drcpractical

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.drcpractical.databinding.ActivityMainBinding
import com.drcpractical.network.model.HomeRequest
import com.drcpractical.network.util.ApiException
import com.drcpractical.network.util.DialogProgress
import com.drcpractical.network.util.NoInternetException
import com.drcpractical.network.util.showToast
import com.drcpractical.network.viewmodel.DashboardViewModel
import com.drcpractical.network.viewmodel.DashboardViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: DashboardViewModelFactory by instance()
    private lateinit var viewModel: DashboardViewModel

    //Binding Controls
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
        homePage()
    }

    private fun homePage() {
        showLoading()
        val request = HomeRequest(customerId = "")
        lifecycleScope.launch {
            try {
                val response = viewModel.hopePage(request)
                if (response != null) {
                    hideLoading()

                }
            } catch (e: ApiException) {
                hideLoading()
                e.message?.let { (this@MainActivity).showToast(it) }
            } catch (e: NoInternetException) {
                hideLoading()
                e.message?.let { (this@MainActivity).showToast(it) }
            }
        }
    }

    var mProgressDialog: DialogProgress? = null

    private fun showLoading() {
        try {
            hideLoading()
            mProgressDialog =
                DialogProgress(this, R.style.progress_dialog_text_style)
            mProgressDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun hideLoading() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog?.dismiss()
            }
        } catch (e: Exception) {
        }
    }


}