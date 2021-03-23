package com.ulys.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.utils.JsonValue
import ktx.assets.toInternalFile
import ktx.log.info
import ktx.log.logger

object DebugCameraConfig {

    // default keys
    private const val DEFAULT_KEY_LEFT = Input.Keys.A
    private const val DEFAULT_KEY_RIGHT = Input.Keys.D
    private const val DEFAULT_KEY_UP = Input.Keys.W
    private const val DEFAULT_KEY_DOWN = Input.Keys.S
    private const val DEFAULT_KEY_QUIT = Input.Keys.Q

    private const val DEFAULT_KEY_ZOOM_IN = Input.Keys.COMMA
    private const val DEFAULT_KEY_ZOOM_OUT = Input.Keys.PERIOD
    private const val DEFAULT_KEY_RESET = Input.Keys.BACKSPACE
    private const val DEFAULT_KEY_LOG = Input.Keys.ENTER

    private const val DEFAULT_ZOOM_SPEED = 2f
    private const val DEFAULT_MAX_ZOOM_IN = 0.2f
    private const val DEFAULT_MAX_ZOOM_OUT = 20f
    private const val DEFAULT_MOVE_SPEED = 20f

    private const val FILE_PATH = "debug/debugCameraConfig.json"
    private val fileHandle = FILE_PATH.toInternalFile()

    // class properties
    private var left_Key: Int = DEFAULT_KEY_LEFT
    private var rightKey: Int = DEFAULT_KEY_RIGHT
    private var up___Key: Int = DEFAULT_KEY_UP
    private var down_Key: Int = DEFAULT_KEY_DOWN
    private var zumInKey: Int = DEFAULT_KEY_ZOOM_IN
    private var zmOutKey: Int = DEFAULT_KEY_ZOOM_OUT
    private var resetKey: Int = DEFAULT_KEY_RESET
    private var log__Key: Int = DEFAULT_KEY_LOG
    private var quit_Key: Int = DEFAULT_KEY_QUIT

    var maxZoomIn: Float = DEFAULT_MAX_ZOOM_IN
    var maxZoomOut: Float = DEFAULT_MAX_ZOOM_OUT
    var moveSpeed: Float = DEFAULT_MOVE_SPEED
    var zoomSpeed: Float = DEFAULT_ZOOM_SPEED

    val isLeftPressed: Boolean
        get() = Gdx.input.isKeyPressed(left_Key)
    val isRightPressed: Boolean
        get() = Gdx.input.isKeyPressed(rightKey)
    val isUpPressed: Boolean
        get() = Gdx.input.isKeyPressed(up___Key)
    val isDownPressed: Boolean
        get() = Gdx.input.isKeyPressed(down_Key)

    val isZoomInPressed: Boolean
        get() = Gdx.input.isKeyPressed(zumInKey)
    val isZoomOutPressed: Boolean
        get() = Gdx.input.isKeyPressed(zmOutKey)
    val isResetPressed: Boolean
        get() = Gdx.input.isKeyPressed(resetKey)
    val isLogPressed: Boolean
        get() = Gdx.input.isKeyPressed(log__Key)
    val isQuitPressed: Boolean
        get() = Gdx.input.isKeyPressed(quit_Key)

    private val log = logger<DebugCameraConfig>()

    init {
        if (fileHandle.exists()) {
            load()
        } else {
            log.info { "debugCameraConfig.json not found, using defaults key mapping" }
        }
    }

    private fun load() {
        val reader = JsonReader()
        val jsonValue = reader.parse(fileHandle)
        left_Key = getValue(jsonValue, "leftKey", DEFAULT_KEY_LEFT)
        rightKey = getValue(jsonValue, "rightKey", DEFAULT_KEY_RIGHT)
        up___Key = getValue(jsonValue, "upKey", DEFAULT_KEY_UP)
        down_Key = getValue(jsonValue, "downKey", DEFAULT_KEY_DOWN)
        quit_Key = getValue(jsonValue, "quit", DEFAULT_KEY_QUIT)
        zumInKey = getValue(jsonValue, "zoomInKey", DEFAULT_KEY_ZOOM_IN)
        zmOutKey = getValue(jsonValue, "zoomOutKey", DEFAULT_KEY_ZOOM_OUT)
        resetKey = getValue(jsonValue, "resetKey", DEFAULT_KEY_RESET)
        log__Key = getValue(jsonValue, "logKey", DEFAULT_KEY_LOG)
        maxZoomIn = jsonValue.getFloat("maxZoomIn")
        maxZoomOut = jsonValue.getFloat("maxZoomOut")
        moveSpeed = jsonValue.getFloat("moveSpeed")
        zoomSpeed = jsonValue.getFloat("zoomSpeed")
    }

    private fun getValue(jsonValue: JsonValue, name: String, default: Int): Int {
        val keyString = jsonValue.getString(name, Input.Keys.toString(default))
        return Input.Keys.valueOf(keyString)
    }

    override fun toString(): String {
        val ls = System.getProperty("line.separator")
        return "Debug camera config : " + ls +
                "zoom range $maxZoomIn to $maxZoomOut $ls" +
                "moveSpeed=$moveSpeed zoomSpeed=$zoomSpeed" + ls +
                "left=${Input.Keys.toString(left_Key)} right=${Input.Keys.toString(rightKey)} " +
                "up=${Input.Keys.toString(up___Key)} down=${Input.Keys.toString(down_Key)} $ls" +
                "zoomIn=${Input.Keys.toString(zumInKey)} zoomOut=${Input.Keys.toString(zmOutKey)} $ls" +
                "reset=${Input.Keys.toString(resetKey)} " +
                "log=" + Input.Keys.toString(log__Key) + " Quit=" + Input.Keys.toString(quit_Key)
    }
}