package krasnikov.project.postsapp.post.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.databinding.FragmentFeedPostsBinding
import krasnikov.project.postsapp.post.create.ui.CreatePostFragment
import krasnikov.project.postsapp.post.feed.ui.adapter.PostAdapter
import krasnikov.project.postsapp.utils.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentFeedPostsBinding

    private val postsViewModel by viewModel<PostsViewModel>()

    private val adapter by lazy { PostAdapter() }

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
        observePosts()
        observeStatusLoadedFromRemote()
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

    private fun observePosts() {
        postsViewModel.posts.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.data)
                }
                is Result.Error -> {
                    showToast(R.string.error_loading)
                }
            }
        }
    }

    private fun observeStatusLoadedFromRemote() {
        postsViewModel.errorLoadFromRemote.observe(viewLifecycleOwner) {
            if (it) {
                showToast(R.string.error_refreshing)
            }
        }
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