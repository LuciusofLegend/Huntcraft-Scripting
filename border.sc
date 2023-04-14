get_border_size() -> (
    if(read_file('border_radius', 'json') == null, write_file('border_radius', 'json', 500));
    return(read_file('border_radius', 'json'))
);

get_spawn_distance(player) -> (
    x = get(query(player, 'pos'), 0);
    z = get(query(player, 'pos'), 1);
    return(sqrt((0-x)^2+(0-z)^2))
);

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(current_dimension() != 'the_nether', return());
    if(block != 'obsidian', return());
    if(item_tuple:0 != 'flint_and_steel', return());

    portal_radius = get_border_size()/8;
    if(get_spawn_distance(player) < portal_radius, return());

    display_title(player, 'actionbar', format('r Light your portal less than ' + round(distance) + ' blocks from spawn'));
    return('cancel')
);

set_border(border_size) -> (

    run('chunky shape circle');
    run('chunky radius ' + border_size);
    run('chunky border add minecraft:overworld');
    run('chunky border add minecraft:the_nether');
    run('chunky border add minecraft:the_end');

    display_title(player(), 'actionbar', 'Border Radius set to ' + border_size);
    delete_file('border_radius', 'json');
    write_file('border_radius', 'json', border_size)
);

expand_border() -> (
    if(get_border_size() >= 5000, return);

    new_size = get_border_size() + 100;

    set_border(new_size);
);

__config() -> {
    'scope' -> 'global',
    'commands' -> {
        'expand' -> 'expand_border',
        'set <border_size>' -> 'set_border'
    },
    'arguments' -> {
        'border_size' -> {'type' -> 'int'}
    }
}
