package krasnikov.project.postsapp.post.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.databinding.FragmentFeedPostsBinding
import krasnikov.project.postsapp.post.create.ui.CreatePostFragment
import krasnikov.project.postsapp.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentFeedPostsBinding

    private val postsViewModel by viewModel<PostsViewModel>()

    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        observeContent()
        setupBtnListeners()
    }

    private fun setupRecycler() {
        val rvPosts = binding.rvPosts
        rvPosts.adapter = adapter
    }

    private fun setupBtnListeners() {
        binding.fabAdd.setOnClickListener {
            navigateToCreateFragment()
        }
    }

    private fun observeContent() {
        postsViewModel.content.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Content -> {
                    hideLoading()
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    hideLoading()
                    showToast(R.string.error_loading)
                }
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.isVisible = true
    }

    private fun hideLoading() {
        binding.pbLoading.isVisible = false
    }

    private fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCreateFragment() {
        parentFragmentManager.commit {
            replace<CreatePostFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostsFragment()
    }
}
