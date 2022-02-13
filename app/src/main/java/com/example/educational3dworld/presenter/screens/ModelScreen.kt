package com.example.educational3dworld.presenter.screens

//import com.google.ar.core.HitResult
//import com.google.ar.core.Plane
//import com.google.ar.sceneform.AnchorNode
//import com.google.ar.sceneform.Node
//import com.google.ar.sceneform.math.Vector3
//import com.google.ar.sceneform.rendering.ModelRenderable
//import com.google.ar.sceneform.rendering.Renderable
//import com.google.ar.sceneform.rendering.ViewRenderable
//import com.google.ar.sceneform.ux.ArFragment
//import com.google.ar.sceneform.ux.TransformableNode
//import com.gorisse.thomas.sceneform.scene.await
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.ScreenModelBinding
import com.example.educational3dworld.presenter.viewmodel.ModelScreenViewModel
import com.example.educational3dworld.presenter.viewmodel.viewmodelimpl.ModelScreenViewModelImpl
import com.example.educational3dworld.utils.showToast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.SceneView
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.gorisse.thomas.sceneform.scene.await
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ModelScreen : Fragment(R.layout.screen_model) {
    private val bind by viewBinding(ScreenModelBinding::bind)
    private lateinit var arFragment: ArFragment
    private val arSceneView get() = arFragment.arSceneView
    private val scene get() = arSceneView.scene

    private var model: Renderable? = null
    private var modelView: ViewRenderable? = null

    private val viewModel: ModelScreenViewModel by viewModels<ModelScreenViewModelImpl>()
    private val args: ModelScreenArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getObjectUrl(args.type, args.id)
        loadFlows()

    }

    private fun arBegging(fileUrl: String) {
        arFragment = (childFragmentManager.findFragmentById(R.id.arFragment) as ArFragment).apply {
            setOnSessionConfigurationListener { session, config ->
                // Modify the AR session configuration here
            }
            setOnViewCreatedListener { arSceneView ->
                arSceneView.setFrameRateFactor(SceneView.FrameRate.FULL)
            }
            setOnTapArPlaneListener(::onTapPlane)
            setOnTapPlaneGlbModel(fileUrl)
        }

    }

    private fun loadFlows() {
        viewModel.modelFlow.onEach {
            showToast(it.fileUrl)
            arBegging(it.fileUrl)
        }.launchIn(lifecycleScope)
    }

    private suspend fun loadModels(fileUrl: String) {
        model = ModelRenderable.builder()
            .setSource(context, Uri.parse("https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb"))
            .setIsFilamentGltf(true).await()
        modelView = ViewRenderable.builder()
            .await()
    }

    private fun onTapPlane(hitResult: HitResult, plane: Plane, motionEvent: MotionEvent) {
        if (model == null || modelView == null) {
            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            return
        }

        // Create the Anchor.
        scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
            // Create the transformable model and add it to the anchor.
            addChild(TransformableNode(arFragment.transformationSystem).apply {
                renderable = model
                renderableInstance.animate(true).start()
                // Add the View
                addChild(Node().apply {
                    // Define the relative position
                    localPosition = Vector3(0.0f, 1f, 0.0f)
                    localScale = Vector3(0.7f, 0.7f, 0.7f)
                    renderable = modelView
                })
            })
        })
    }

}