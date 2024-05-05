$Script = Join-Path $PSScriptRoot 'script.ps1'
Set-PSBreakpoint -Script $Script -Line 7 -Action { ((Get-PSCallStack | foreach { "at {0}, {1}" -f ($_.Command,$_.Location) }) -join "`n") | Out-Host } | Out-Null
& $Script