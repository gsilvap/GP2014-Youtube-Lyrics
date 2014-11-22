GP2014 Youtube Lyrics
=====================

### Resumo de atividade

#### Tarefas executadas:
  - [APIs](#apis)
  - [Validação do URL](#validacao-do-url)
  - [Download de metadados](#download-de-metadados)
    * [Ler regex e pattern de ficheiro](#ler-regex-e-pattern-de-ficheiro)
  - [Download Video MP4](#download-video-mp4)
    * [Execução de 5 pedidos de cada vez recorrendo a Threads](#execucao-de-5-pedidos-de-cada-vez-recorrendo-a-threads)

#### Tarefas a executar:
  - [Selecionar o melhor formato em MP4](#selecionar-o-melhor-formato-em-mp4)
  - [Pós-tratamento de metadados](#pos-tratamento-de-metadados)
  - [Criação do Crawler para 3 a 5 sites diferentes](#criacao-do-crawler-para-3-a-5-sites-diferentes)
  - [Download da letra da música](#download-da-letra-da-musica)

### Testes
  - Videos invalidos
  - Playlists

### APIs

| N | API's                 | Func? | Linguagem  |
|---|-----------------------|-------|------------|
|   | [vget / wget](https://github.com/axet/vget)           | Não.                                     | Java       |
|   | [JDownloader](http://jdownloader.org/knowledge/wiki/development/get-started)           |         | Java       |
|   | JavaYoutubeDownloader | Não.                                                                 | Java       |
|   | [YTD2](http://ytd2.sourceforge.net/)                  | Não.                                     | Java       |
|   | [KeepVid](http://keepvid.com/)               |                                                   | Javascript |
|   | [get-flash-videos](https://code.google.com/p/get-flash-videos/)      |                           | Perl       |
| 1 | [youtube-dl](#1-youtube-dl)            | Sim.                               | Python     |
| 2 | [PAFY](#2-pafy)                  | Sim.                                      | Python     |
| 3 | [mps-youtube](#3-mps-youtube)           | Sim.                               | Python     |

###### 1. youtube-dl
  - Biblioteca em python
  - Pode executar atravês da consola depois de instalada
  - Formatos de video:
    * mp4, 3gp, flv, webm
  - Formatos de audio:
    * m4a, webm
  - Corre em python 2 e em python 3
  - [+ informação](https://github.com/rg3/youtube-dl)

###### 2. PAFY
  - Biblioteca em python
  - Formatos de video:
    * mp4, 3gp, flv, webm, m4v
  - Formatos de audio:
    * m4a, ogg
  - Corre em python 3 (corre em python 2?)
  - [+ informação](https://github.com/np1/pafy)
  

###### 3. MPS-Youtube
  - Corre em python 2 e em python 3
  - Requer o mplayer ou o mpv
  - Possibilita a pesquisa de videos
  - [+ informação](https://github.com/np1/mps-youtube)

###### A framework escolhida foi a primeira, uma vez que a mesma mostrou ter diversas potencialidades ao nivel do download de informação e de videos do youtube.

### Validacao do URL
#### Ler regex e pattern de ficheiro

Como é feita a validação dos links?
Que links estão a ser verificados?

### Download de metadados
texto
### Download Video MP4

Neste momento o programa está a fazer o download do video no melhor formato.

#### Execução de 5 pedidos de cada vez recorrendo a Threads
texto
### Selecionar o melhor formato em MP4
texto
### Pos-tratamento de metadados
texto
### Download da letra da musica
texto
### Criacao do Crawler para 3 a 5 sites diferentes
  - http://www.azlyrics.com/
  - http://www.metrolyrics.com/
  - http://www.songlyrics.com/
  - http://www.lyricsmode.com/
  - http://www.lyricsfreak.com/


Download do audio em mp3
 youtube-dl -x --audio-format mp3 https://www.youtube.com/watch?v=1YWDLjvfEs4

Download da thumbnail
 youtube-dl --write-thumbnail https://www.youtube.com/watch?v=1YWDLjvfEs4

 C:\Users\GonçaloSilva> youtube-dl -f 135 --write-thumbnail -k -x --audio-quality 0 --audio-format mp3 https://www.youtube.com/watch?v=1YWDLj
vfEs4

youtube-dl -citk --max-quality FORMAT --extract-audio --audio-format mp3 http://www.youtube.com/playlist?list=XXXXXXXXXXX