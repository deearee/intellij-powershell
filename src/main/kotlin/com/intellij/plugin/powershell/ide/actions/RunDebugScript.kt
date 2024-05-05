package com.intellij.plugin.powershell.ide.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.ui.Messages
import com.intellij.plugin.powershell.ide.run.findPsExecutable
import kotlin.concurrent.thread


class RunDebugScript : AnAction() {
  private val LOG = Logger.getInstance(javaClass)

  override fun actionPerformed(e: AnActionEvent) {
    val scriptPath = Messages.showInputDialog(e.project, "Enter debug script path", "Debug Script Path",null) ?: return

    thread {
      val process = Runtime.getRuntime().exec(arrayOf(findPsExecutable(), scriptPath))
      LOG.info("Running $scriptPath....")

      process.inputStream.bufferedReader().forEachLine {
        LOG.info(it)
      }
    }
  }
}
