GP2014-Youtube-Lyrics
=====================

  Tarefas executadas:
  =========================
  - Validação do URL
  - Download de metadados
  - Download Video MP4
  
  - Execução de 5 pedidos de cada vez recorrendo a Threads

  Tarefas a executar:
  - Selecionar o melhor formato em MP4
  - Pós-tratamento de metadados
  - Download da letra da música
  - Criação do Crawler para 3 a 5 sites diferentes



Projeto de GP

| N | API's                 | Func? | URL                                                           | Linguagem  |
|---|-----------------------|-------|---------------------------------------------------------------|------------|
|   | vget / wget           | Não.  | https://github.com/axet/vget                                  | Java       |
|   | JDownloader           |       | http://jdownloader.org/knowledge/wiki/development/get-started | Java       |
|   | JavaYoutubeDownloader | Não.  |                                                               | Java       |
|   | YTD2                  | Não.  | http://ytd2.sourceforge.net/                                  | Java       |
|   | KeepVid               |       | http://keepvid.com/                                           | Javascript |
|   | get-flash-videos      |       | https://code.google.com/p/get-flash-videos/                   | Perl       |
| 1 | youtube-dl            | Sim.  | https://github.com/rg3/youtube-dl/                            | Python     |
| 2 | PAFY                  | Sim.  | https://github.com/np1/pafy                                   | Python     |
| 3 | mps-youtube           | Sim.  | https://github.com/np1/mps-youtube                            | Python     |

1. youtube-dl
  - Biblioteca em python
  - Pode executar atravês da consola depois de instalada
  - Formatos de video:
    * mp4, 3gp, flv, webm
  - Formatos de audio:
    * m4a, webm
  - Corre em python 2 e em python 3

2. PAFY
  - Biblioteca em python
  - Formatos de video:
    * mp4, 3gp, flv, webm, m4v
  - Formatos de audio:
    * m4a, ogg
  - Corre em python 3 (corre em python 2?)

3. MPS-Youtube
  - Corre em python 2 e em python 3
  - Requer o mplayer ou o mpv
  - Possibilita a pesquisa de 
