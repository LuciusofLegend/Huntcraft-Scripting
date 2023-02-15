__config() -> {
	'commands' -> {
    	'choose' -> 'pronouns_screen'
    },
    'scope' -> 'global'
};

__on_player_connects(player) -> (
	if(statistic(player, 'custom', 'time_played') > 1,
    	return()
    );

	print(player, format('eb Welcome to Huntcraft!'));
	pronouns_screen()
);

pronouns_screen() -> (
	pronoun1 = [[1, 'He'], [2, 'They'], [3, 'She']];
    pronoun2 = [[7, 'Him'], [8, 'Them'], [9, 'Her']];

	screen = create_screen(
    	player(),
        'crafting',
        'Pronoun Selector',

        _(screen, player, action, data) -> (
        	if(action != 'pickup', return('cancel'));

            if(inventory_get(screen, data:'slot'):0 == 'red_concrete',
				if(data:'slot' < 4, for(pronoun1, inventory_set(screen, _:0, 1, 'red_concrete')));
				if(data:'slot' > 6, for(pronoun2, inventory_set(screen, _:0, 1, 'red_concrete')));

            	inventory_set(screen, data:'slot', 1, 'green_concrete');
            );
            
            if(inventory_get(screen, data:'slot'):0 == 'paper',
            	result = first(pronoun1, inventory_get(screen, _:0):0 == 'green_concrete'):1 + '/' + first(pronoun2, inventory_get(screen, _:0):0 == 'green_concrete'):1;
				print(result);
				
				run('lp user ' + player + ' meta addsuffix ' + result);
			);
            'cancel'
        )
    );

	inventory_set(screen, 0, 1, 'paper');
    for(pronoun1, inventory_set(screen, _:0, 1, 'red_concrete'));
    for(pronoun2, inventory_set(screen, _:0, 1, 'red_concrete'));
)