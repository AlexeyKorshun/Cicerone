package com.github.terrakok.cicerone

/**
 * BaseRouter is an abstract class to implement high-level navigation.
 *
 * Extend it to add needed transition methods.
 */
abstract class BaseRouter {
    internal val commandBuffer = CommandBuffer()
    private val resultBus = ResultBus()

    /**
     * Sets data listener with given key.
     *
     * After first call listener will be removed.
     */
    fun setResultListener(key: String, listener: (data: Any) -> Unit) {
        resultBus.setResultListener(key, listener)
    }

    /**
     * Sends data to listener with given key.
     */
    fun sendResult(key: String, data: Any) {
        resultBus.sendResult(key, data)
    }

    /**
     * Sends navigation command array to [CommandBuffer].
     *
     * @param commands navigation command array to execute
     */
    protected fun executeCommands(vararg commands: Command) {
        commandBuffer.executeCommands(commands)
        resultBus.flush()
    }
}