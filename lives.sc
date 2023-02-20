set_lives(player, lives) -> (
    delete_file(player, 'json');
    write_file(player, 'json', lives);
);

query_lives(player) -> (
    return(read_file(player, 'json'))
);

print_lives(player) -> (
    print(query_lives(player))
);

revive_menu() -> (
    return('Does nothing yet')
);

revive_player(player, revivor) -> (
    return('Does nothing yet')
);

__on_player_dies(player) -> (
    new_lives = query_lives(player) - 1;
    if(new_lives == 0,
        run('ban ' + player + 'Your soul has been obliterated');
        return()
    );

    set_lives(player, new_lives)
);

__on_player_connects(player) -> (
    if(query_lives(player) == null,
        set_lives(player, 5);
    )
);

__config() -> {
    'scope' -> 'global',
    'commands' -> {
        'query <player>' -> 'print_lives'
    },
    'arguments' -> {
        'player' -> {'type' -> 'players', 'single'}
    }
}
