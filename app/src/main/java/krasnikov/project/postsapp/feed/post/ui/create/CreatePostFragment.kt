package krasnikov.project.postsapp.feed.post.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import krasnikov.project.postsapp.app.App
import krasnikov.project.postsapp.databinding.FragmentCreatePostBinding
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment : Fragment() {

    private lateinit var binding: FragmentCreatePostBinding

    private val createPostViewModel by viewModel<CreatePostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnListeners()
    }

    private fun setupBtnListeners() {
        binding.btnCreate.setOnClickListener {
            createPostViewModel.savePost(createPostModel())
            parentFragmentManager.popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun createPostModel(): PostEntity {
        return PostEntity(
            userId = App.userId,
            title = binding.etTitle.text.toString(),
            body = binding.etBody.text.toString(),
            isLocal = true
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreatePostFragment()
    }
}