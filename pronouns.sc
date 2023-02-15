__config() -> {
	'commands' -> {
    	'choose' -> 'choose_pronouns'
    },
    'scope' -> 'global'
};

__on_player_connects(player) -> (
	if(first(read_file('players', 'json'), _ == player) == null,
    	print(player, format('eb Welcome to Huntcraft! ', 'e '))
    );
	choose_pronouns()
);

choose_pronouns() -> (
	create_screen(
    	player(),
        'generic_9x5',
        'Pronoun Selector',
        _(screen, player, action, data) -> (
        	if(action == 'pickup',
            	print(data:'slot')
            )
        )
    );
    
    write_file('players', 'json', player());

	//run('lp user ' + player() + ' meta removesuffix');
	//run('lp user ' + player() + ' meta addsuffix 50 ' + pronoun1 + '/' + pronoun2)
)
